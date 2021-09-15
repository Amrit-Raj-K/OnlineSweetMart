import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderbillComponent } from './orderbill.component';

describe('OrderbillComponent', () => {
  let component: OrderbillComponent;
  let fixture: ComponentFixture<OrderbillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderbillComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderbillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
