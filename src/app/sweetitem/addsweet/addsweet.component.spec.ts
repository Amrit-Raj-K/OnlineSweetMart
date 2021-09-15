import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddsweetComponent } from './addsweet.component';

describe('AddsweetComponent', () => {
  let component: AddsweetComponent;
  let fixture: ComponentFixture<AddsweetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddsweetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddsweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
