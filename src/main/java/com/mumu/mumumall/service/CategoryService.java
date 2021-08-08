package com.mumu.mumumall.service;

import com.mumu.mumumall.model.request.AddCategoryReq;
import com.mumu.mumumall.model.request.UpdateCategoryReq;

public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(UpdateCategoryReq updateCategoryReq);
}
