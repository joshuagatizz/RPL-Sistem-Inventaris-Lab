package com.example.sisteminventarislab.web.model.Response;

import com.example.sisteminventarislab.entity.Barang;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBarangWebResponse {
  String status;
  Barang barang;
}
