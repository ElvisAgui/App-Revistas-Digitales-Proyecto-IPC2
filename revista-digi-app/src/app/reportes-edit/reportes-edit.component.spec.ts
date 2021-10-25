import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportesEditComponent } from './reportes-edit.component';

describe('ReportesEditComponent', () => {
  let component: ReportesEditComponent;
  let fixture: ComponentFixture<ReportesEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportesEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportesEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
