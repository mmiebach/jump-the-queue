import { Component, OnInit } from '@angular/core';
import { AccessCode, Queue } from '../shared/backendModels/interfaces';
import { Observable, of, throwError } from 'rxjs';
import { AccessCodeService } from './services/access-code.service';
import { tap, catchError } from 'rxjs/operators';
import { AuthService } from '../core/authentication/auth.service';
import { QueueService } from './services/queue.service';
import { RemainingCodes } from '../shared/backendModels/interfaces';

@Component({
  selector: 'app-view-queue',
  templateUrl: './view-queue.component.html',
  styleUrls: ['./view-queue.component.css']
})
export class ViewQueueComponent implements OnInit {

  accessCodeAttended$: Observable<AccessCode>;
  accessCodeVisitor$: Observable<AccessCode>;
  queue$: Observable<Queue>;
  remaining: number;
  activeQueueId: number;

  constructor(private accessCodeService: AccessCodeService, private queueService: QueueService, private authService: AuthService) { }

  ngOnInit() {
    this.accessCodeAttended$ = this.accessCodeService.getCurrentlyAttendedAccessCode();
    this.accessCodeVisitor$ = this.accessCodeService.getVisitorAccessCode(this.authService.getUserId());
    this.queue$ = this.queueService.getActiveQueue();
    this.queueService.getActiveQueue().subscribe(
      (q) => {
        this.activeQueueId = q.id;
      }
    );
  }

  onRemainingCodes() {
    this.accessCodeService.getRemainingCodesCount(this.authService.getUserId(), this.activeQueueId).subscribe(
      (r) => {
        this.remaining = r.remainingCodes;
      }
    );
    return this.remaining;
  }

  onJoinQueue(queueId: number): void {
    this.accessCodeVisitor$ = this.accessCodeService.saveAccessCode(this.authService.getUserId(), queueId);
    this.onRemainingCodes();
  }

  onLeaveQueue(accessCodeId: number): void {
    this.accessCodeService.deleteAccessCode(accessCodeId);
    this.accessCodeVisitor$ = null;
  }
}
