package app.service;

import app.entity.Phone;
import app.repo.PhoneRepo;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

  private final PhoneRepo phoneRepo;

  public PhoneService(PhoneRepo phoneRepo) {
    this.phoneRepo = phoneRepo;
  }

  public Phone fetch(long id) {
    return phoneRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
  }

}
