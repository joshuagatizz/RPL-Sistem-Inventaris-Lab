package com.example.sisteminventarislab.web.model.Response;

import com.example.sisteminventarislab.entity.Barang;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBarangResponse {
  String status;
  Barang barang;
}
