import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LikedActComponent } from './liked-act.component';

describe('LikedActComponent', () => {
  let component: LikedActComponent;
  let fixture: ComponentFixture<LikedActComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LikedActComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LikedActComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
