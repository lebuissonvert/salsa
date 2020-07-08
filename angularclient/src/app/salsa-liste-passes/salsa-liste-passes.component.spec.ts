import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalsaListePassesComponent } from './salsa-liste-passes.component';

describe('SalsaListePassesComponent', () => {
  let component: SalsaListePassesComponent;
  let fixture: ComponentFixture<SalsaListePassesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalsaListePassesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalsaListePassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
