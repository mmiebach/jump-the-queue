import { Component, OnInit } from '@angular/core';
import { AccessCode, Queue } from '../shared/backendModels/interfaces';
import { Observable } from 'rxjs';
import { AccessCodeService } from './services/access-code.service';
import { AuthService } from '../core/authentication/auth.service';
import { QueueService } from './services/queue.service';

@Component({
  selector: 'app-view-queue',
  templateUrl: './view-queue.component.html',
  styleUrls: ['./view-queue.component.css']
})
export class ViewQueueComponent implements OnInit {

  accessCodeAttended$: Observable<AccessCode>;
  accessCodeVisitor$: Observable<AccessCode>;
  queue$: Observable<Queue>;
  estimatedTime: number;

  constructor(private accessCodeService: AccessCodeService, private queueService: QueueService, private authService: AuthService) { }

  ngOnInit() {
    this.accessCodeAttended$ = this.accessCodeService.getCurrentlyAttendedAccessCode();
    this.accessCodeVisitor$ = this.accessCodeService.getVisitorAccessCode(this.authService.getUserId());
    this.queue$ = this.queueService.getActiveQueue();
  }

  onJoinQueue(queueId: number): void {
    this.accessCodeVisitor$ = this.accessCodeService.saveAccessCode(this.authService.getUserId(), queueId);
  }

  onLeaveQueue(accessCodeId: number): void {
    this.accessCodeService.deleteAccessCode(accessCodeId);
    this.accessCodeVisitor$ = null;
  }

  EstimatedTime(queueId: number, $interval) {
    const accessCode: AccessCode = new AccessCode();
    accessCode.visitorId =  this.authService.getUserId();
    accessCode.queueId = queueId;
    this.accessCodeService.getEstimatedTime(accessCode).subscribe(
      (e) => {
        this.estimatedTime = e.estimatedTime;
      }
    );
    return this.estimatedTime;
  }
}
