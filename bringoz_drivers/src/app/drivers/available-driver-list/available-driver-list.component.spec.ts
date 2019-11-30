import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableDriverListComponent } from './available-driver-list.component';

describe('AvailableDriverListComponent', () => {
  let component: AvailableDriverListComponent;
  let fixture: ComponentFixture<AvailableDriverListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvailableDriverListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableDriverListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
