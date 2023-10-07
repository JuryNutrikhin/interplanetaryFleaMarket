package com.example.interplanetaryfleamarket.rdb;

import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.rdb.repository.ClientRepository;
import com.example.interplanetaryfleamarket.servise.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RdbClientService implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<Client> add(Client client) {
        if(clientRepository.findByClientName(client.getClientName()).isPresent()){
            return Optional.empty();// такой пользователь уже существует
        }
        return Optional.of(clientRepository.save(client));
    }

    @Override
    public Optional<Client> deleteById(Integer id) {
        Optional<Client>  removable = findById(id);
        if(removable.isPresent()){
            clientRepository.deleteById(id);
        }
        return removable;// вернем удаленный (empty если не с таким id)
    }

    @Override
    public Optional<Client> updateById(Integer id, Client client) {
        Optional<Client> updated = findById(id);

        Optional<Client> duplicatedByNameClient = clientRepository.findByClientName(client.getClientName());
        if (updated.isPresent() && (duplicatedByNameClient.isEmpty()) || Objects.equals(duplicatedByNameClient.get().getId(), id)) {
            client.setId(id);
            client.setRegistration(updated.get().getRegistration());
            return  Optional.of(clientRepository.save(client));
        }else {
            return Optional.empty();
        }

    }

}
