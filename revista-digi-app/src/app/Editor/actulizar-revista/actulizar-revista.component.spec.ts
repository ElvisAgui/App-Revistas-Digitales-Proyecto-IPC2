import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActulizarRevistaComponent } from './actulizar-revista.component';

describe('ActulizarRevistaComponent', () => {
  let component: ActulizarRevistaComponent;
  let fixture: ComponentFixture<ActulizarRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActulizarRevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActulizarRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
