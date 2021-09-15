import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletesweetComponent } from './deletesweet.component';

describe('DeletesweetComponent', () => {
  let component: DeletesweetComponent;
  let fixture: ComponentFixture<DeletesweetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletesweetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletesweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
