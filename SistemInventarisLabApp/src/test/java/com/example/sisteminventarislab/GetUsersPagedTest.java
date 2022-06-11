package com.example.sisteminventarislab;

import com.example.sisteminventarislab.entity.User;
import com.example.sisteminventarislab.repository.UserRepository;
import com.example.sisteminventarislab.repository.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class GetUsersPagedTest extends SistemInventarisLabApplicationTests{

  private final UserRepository repository;

  @Test
  public void getUsersPaged_success() {
    repository.save(User.builder().build());
  }

  @Test
  public void getUsersPaged_failed_negativeInput() {

  }

  @Test
  public void getUsersPaged_failed_emptyInput() {

  }

  @Test
  public void getUsersPaged_failed_inputExceedsPageLimit() {

  }
}
