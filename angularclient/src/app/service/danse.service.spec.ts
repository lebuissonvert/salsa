import { TestBed } from '@angular/core/testing';

import { DanseService } from './danse.service';

describe('DanseService', () => {
  let service: DanseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DanseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
