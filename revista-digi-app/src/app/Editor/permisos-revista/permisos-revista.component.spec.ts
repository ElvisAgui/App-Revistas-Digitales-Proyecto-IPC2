import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PermisosRevistaComponent } from './permisos-revista.component';

describe('PermisosRevistaComponent', () => {
  let component: PermisosRevistaComponent;
  let fixture: ComponentFixture<PermisosRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PermisosRevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PermisosRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
