package br.com.brinquedos.controller;

import br.com.brinquedos.entity.Brinquedo;
import br.com.brinquedos.service.BrinquedoService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoService service;

    @GetMapping
    public ResponseEntity<List<EntityModel<Brinquedo>>> listar() {
        List<EntityModel<Brinquedo>> brinquedos = service.get().stream()
                .map(brinquedo -> EntityModel.of(brinquedo,
                        createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).consultarId(brinquedo.getId()), "self"),
                        createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).listar(), "brinquedos")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(brinquedos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> consultarId(@PathVariable Long id) {
        Brinquedo brinquedo = service.getId(id)
                .orElseThrow(() -> new IllegalArgumentException("Brinquedo Não Encontrado"));

        EntityModel<Brinquedo> resource = EntityModel.of(brinquedo,
                createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).consultarId(id), "self"),
                createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).listar(), "brinquedos"));

        return ResponseEntity.ok(resource);
    }


    @PostMapping
    public ResponseEntity<EntityModel<Brinquedo>> criarBrinquedo(@RequestBody Brinquedo brinquedo) {
        Brinquedo novoBrinquedo = service.post(brinquedo);
        EntityModel<Brinquedo> resource = EntityModel.of(novoBrinquedo,
                createLinkWithMethod("POST", WebMvcLinkBuilder.methodOn(BrinquedoController.class).consultarId(novoBrinquedo.getId()), "self"),
                createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).listar(), "brinquedos"));
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> atualizarBrinquedo(@PathVariable Long id, @RequestBody Brinquedo brinquedo){
        Brinquedo brinquedoAtualizado = service.put(brinquedo, id);
        EntityModel<Brinquedo> resource = EntityModel.of(brinquedoAtualizado,
                createLinkWithMethod("PUT", WebMvcLinkBuilder.methodOn(BrinquedoController.class).atualizarBrinquedo(id, brinquedo), "self"),
                createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).consultarId(id), "consultar"),
                createLinkWithMethod("GET", WebMvcLinkBuilder.methodOn(BrinquedoController.class).listar(), "brinquedos"));
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(@PathVariable Long id) {
        boolean brinquedoExistente = service.getId(id).isPresent();
        if (brinquedoExistente) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.HEAD).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> path(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Brinquedo brinquedoAtualizado = service.path(id, updates);

        EntityModel<Brinquedo> resource = EntityModel.of(brinquedoAtualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BrinquedoController.class).consultarId(id)).withRel("consultar"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BrinquedoController.class).listar()).withRel("brinquedos"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BrinquedoController.class).path(id, updates)).withSelfRel());

        return ResponseEntity.ok(resource);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> consultarBrinquedoHead(@PathVariable Long id) {
        Optional<Brinquedo> brinquedo = service.getId(id);
        if (!brinquedo.isPresent()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }


    // Método auxiliar para criar links HATEOAS com método HTTP
    private Link createLinkWithMethod(String method, Object invocationValue, String rel) {
        return Link.of(WebMvcLinkBuilder.linkTo(invocationValue).toUri().toString(), rel)
                .withTitle(method + " method");
    }
}
