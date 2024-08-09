package br.com.brinquedos.service;


import br.com.brinquedos.entity.Brinquedo;
import br.com.brinquedos.repository.BrinquedoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrinquedoService {

    @Autowired
    private BrinquedoRepository repository;

    public List<Brinquedo> get() { return repository.findAll(); }

    public Optional<Brinquedo> getId (Long id){
        return repository.findById(id);
    }

    public void delete ( Long Id) {
        repository.deleteById(Id);
    }

    public Brinquedo post (Brinquedo brinquedo) {
        return repository.save(brinquedo);
    }

    public Brinquedo put (Brinquedo brinquedo, Long id){
            return repository.findById(id)
        .map(BrinquedoExiste ->{
            BrinquedoExiste.setNome(brinquedo.getNome());
            BrinquedoExiste.setTipo(brinquedo.getTipo());
            BrinquedoExiste.setPreco(brinquedo.getPreco());
            BrinquedoExiste.setTamanho(brinquedo.getTamanho());
            BrinquedoExiste.setClassificacao(brinquedo.getClassificacao());
        return repository.save(BrinquedoExiste);
        }).orElseThrow(()-> new IllegalArgumentException(id + "nao existente"));
    }

    public Brinquedo path(Long id, Map<String, Object> updates) {
        return repository.findById(id)
                .map(BrinquedoExiste -> {
                    updates.forEach((key, value) -> {
                        try {
                            Field field = Brinquedo.class.getDeclaredField(key);
                            if (field != null) {
                                field.setAccessible(true);
                                field.set(BrinquedoExiste, value);
                            } else {
                                System.out.println("Field not found for key: " + key);
                            }
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            System.err.println("Error updating field: " + key);
                            e.printStackTrace();
                        }
                    });
                    return repository.save(BrinquedoExiste);
                }).orElseThrow(() -> new IllegalArgumentException("Brinquedo com ID " + id + " nao existe."));
    }

}
