import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuscripHomeComponent } from './suscrip-home.component';

describe('SuscripHomeComponent', () => {
  let component: SuscripHomeComponent;
  let fixture: ComponentFixture<SuscripHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuscripHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuscripHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
