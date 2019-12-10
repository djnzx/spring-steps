package app.service;

import app.entity.Message;
import app.repo.MessageDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageDbService {

  private final MessageDbRepository messageDbRepository;

  @Autowired
  public MessageDbService(MessageDbRepository messageDbRepository) {
    this.messageDbRepository = messageDbRepository;
  }

  public Iterable<Message> get_all() {
    return messageDbRepository.findAll();
  }

  public Message get_one(int id) {
    return messageDbRepository.findById(id).orElse(null);
  }

  public Message put_one(Message message) {
    return messageDbRepository.save(message);
  }

  public void del_one(int id) {
    messageDbRepository.deleteById(id);
  }

  private Iterable<Message> get_by(String name) {
    return messageDbRepository.findMessagesByContentContaining(name);
  }
}
