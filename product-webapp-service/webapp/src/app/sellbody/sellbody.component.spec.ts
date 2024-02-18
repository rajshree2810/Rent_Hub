import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellbodyComponent } from './sellbody.component';

describe('SellbodyComponent', () => {
  let component: SellbodyComponent;
  let fixture: ComponentFixture<SellbodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellbodyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellbodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
