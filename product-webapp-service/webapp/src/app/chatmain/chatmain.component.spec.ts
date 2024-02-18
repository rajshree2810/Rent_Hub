import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatmainComponent } from './chatmain.component';

describe('ChatmainComponent', () => {
  let component: ChatmainComponent;
  let fixture: ComponentFixture<ChatmainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChatmainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChatmainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
