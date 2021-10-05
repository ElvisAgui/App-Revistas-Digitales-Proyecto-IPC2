import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicRevistaComponent } from './public-revista.component';

describe('PublicRevistaComponent', () => {
  let component: PublicRevistaComponent;
  let fixture: ComponentFixture<PublicRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicRevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
