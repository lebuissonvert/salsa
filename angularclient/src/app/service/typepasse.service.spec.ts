import { TestBed } from '@angular/core/testing';

import { TypePasseService } from './typepasse.service';

describe('TypePasseService', () => {
  let service: TypePasseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypePasseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
