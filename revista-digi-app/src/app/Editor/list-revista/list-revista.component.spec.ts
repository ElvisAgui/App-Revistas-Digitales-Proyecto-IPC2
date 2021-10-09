import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRevistaComponent } from './list-revista.component';

describe('ListRevistaComponent', () => {
  let component: ListRevistaComponent;
  let fixture: ComponentFixture<ListRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListRevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
