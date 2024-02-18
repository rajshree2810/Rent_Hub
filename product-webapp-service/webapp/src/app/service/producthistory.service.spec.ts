import { TestBed } from '@angular/core/testing';

import { ProducthistoryService } from './producthistory.service';

describe('ProducthistoryService', () => {
  let service: ProducthistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProducthistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
