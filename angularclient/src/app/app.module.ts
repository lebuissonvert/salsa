import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MultiSelectModule } from 'primeng/multiselect';
import { FieldPipe } from './salsa-liste-passes/field.pipe';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { SalsaListePassesComponent } from './salsa-liste-passes/salsa-liste-passes.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FullCalendarModule } from 'primeng/fullcalendar';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng';
import {InputTextareaModule} from 'primeng/inputtextarea';

@NgModule({
  declarations: [
    AppComponent,
    SalsaListePassesComponent,
    FieldPipe
  ],
  imports: [
    BrowserModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MultiSelectModule,
    FontAwesomeModule,
    FullCalendarModule,
    TableModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    DialogModule,
    ButtonModule,
    InputTextareaModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
