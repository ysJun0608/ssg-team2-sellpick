package com.ssg.wsmt.inventory.service;

import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.dto.WhPageResponseDTO;

public interface WarehouseInsertReleaseService {
    WhPageResponseDTO<WarehouseInsertReleaseDTO> findAllInsertReleaseList(Long warehouseId, WhPageRequestDTO pageRequestDTO);
}
