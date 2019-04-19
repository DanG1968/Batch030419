import {RouteGuardService} from './services/route-guard.service';
import {RouterModule, Routes} from '@angular/router';
import {LogoutComponent} from './components/logout/logout.component';
import {LoginComponent} from './components/login/login.component';
import {NgModule} from '@angular/core';
import {ErrorComponent} from './components/error/error.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {ListEmployeesComponent} from './components/list-employees-component/list-employees-component';
import {EmployeeComponent} from './components/employee-component/employee-component';
import {ReimbursementComponent} from './components/reimbursement/reimbursement.component';
import {ListReimbursementsComponent} from './components/list-reimbursements/list-reimbursements.component';


const routes: Routes = [
  { path: '', component: LoginComponent  },
  { path: 'login', component: LoginComponent },
  { path: 'employee', component: EmployeeComponent},
  { path: 'reimbursement', component: ReimbursementComponent},
  { path: 'list-employees', component: ListEmployeesComponent},
  { path: 'list-reimbursements', component: ListReimbursementsComponent},
  { path: 'welcome/:name', component: WelcomeComponent, canActivate: [RouteGuardService]},
  { path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService] },
  { path: 'employee/:employeeId', component: EmployeeComponent },
  { path: '**', component: ErrorComponent }
   ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
