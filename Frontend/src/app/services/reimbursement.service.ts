import { Injectable } from '@angular/core';
import {ERS_JPA_API_URL} from '../app.constants';
import {HttpClient} from '@angular/common/http';
import {Employee} from '../components/list-employees-component/list-employees-component';
import {Reimbursement} from '../components/list-reimbursements/list-reimbursements.component';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllReimbursements() {
    return this.http.get<Reimbursement[]>(`${ERS_JPA_API_URL}/api/reimbursements`);

  }

  deleteReimbursement(id) {
    return this.http.delete(`${ERS_JPA_API_URL}/api/reimbursements/delete?id=${id}`);
  }

  retrieveReimbursement(id) {
    console.log('inside retrieve reimbursements');
    return this.http.get<Reimbursement>(`${ERS_JPA_API_URL}/api/reimbursements/id?id=${id}`);
  }

  updateReimbursement(id, reimbursement) {
    return this.http.put(
      `${ERS_JPA_API_URL}/api/reimbursements`
      , reimbursement);
  }

  createReimbursement(reimbursement) {
    return this.http.post(
      `${ERS_JPA_API_URL}/api/reimbursements/add`
      , reimbursement);
  }

}
