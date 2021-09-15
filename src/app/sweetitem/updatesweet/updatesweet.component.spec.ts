import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatesweetComponent } from './updatesweet.component';

describe('UpdatesweetComponent', () => {
  let component: UpdatesweetComponent;
  let fixture: ComponentFixture<UpdatesweetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatesweetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatesweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
