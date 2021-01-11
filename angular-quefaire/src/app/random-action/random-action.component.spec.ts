import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomActionComponent } from './random-action.component';

describe('RandomActionComponent', () => {
  let component: RandomActionComponent;
  let fixture: ComponentFixture<RandomActionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomActionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
