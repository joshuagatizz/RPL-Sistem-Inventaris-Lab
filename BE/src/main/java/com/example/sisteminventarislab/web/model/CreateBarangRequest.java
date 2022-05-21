package com.example.sisteminventarislab.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBarangRequest {
    String urlFoto;
    String nama;
    String deskripsi;
}
