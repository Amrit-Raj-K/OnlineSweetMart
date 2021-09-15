import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SweetitemComponent } from './sweetitem.component';

describe('SweetitemComponent', () => {
  let component: SweetitemComponent;
  let fixture: ComponentFixture<SweetitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SweetitemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SweetitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
