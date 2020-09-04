import { Component, OnInit } from '@angular/core';
import { AccessCode, Queue, EstimatedTime } from '../shared/backendModels/interfaces';
import { Observable } from 'rxjs';
import { AccessCodeService } from './services/access-code.service';
import { AuthService } from '../core/authentication/auth.service';
import { QueueService } from './services/queue.service';
import { queue } from 'rxjs/internal/scheduler/queue';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-view-queue',
  templateUrl: './view-queue.component.html',
  styleUrls: ['./view-queue.component.css']
})
export class ViewQueueComponent implements OnInit {

  accessCodeAttended$: Observable<AccessCode>;
  accessCodeVisitor$: Observable<AccessCode>;
  queue$: Observable<Queue>;
  estimatedTime$: Observable<EstimatedTime>;
  constructor(private accessCodeService: AccessCodeService, private queueService: QueueService, private authService: AuthService) { }

  ngOnInit() {
    this.accessCodeAttended$ = this.accessCodeService.getCurrentlyAttendedAccessCode();
    this.accessCodeVisitor$ = this.accessCodeService.getVisitorAccessCode(this.authService.getUserId());
    this.queue$ = this.queueService.getActiveQueue();
    setInterval(() => {
      this.EstimatedTime();
      }, 10000);
  }

  onJoinQueue(queueId: number): void {
    this.accessCodeVisitor$ = this.accessCodeService.saveAccessCode(this.authService.getUserId(), queueId);
  }

  onLeaveQueue(accessCodeId: number): void {
    this.accessCodeService.deleteAccessCode(accessCodeId);
    this.accessCodeVisitor$ = null;
  }


  EstimatedTime() {
    const accessCode: AccessCode = new AccessCode();
    this.queueService.getActiveQueue().subscribe(
      // tslint:disable-next-line: no-shadowed-variable
      queue => accessCode.queueId = queue.id
    );
    accessCode.visitorId =  this.authService.getUserId();
    this.estimatedTime$ = this.accessCodeService.getEstimatedTime(accessCode);
    }
  }
