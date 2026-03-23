package com.example.categoria.controllers;

import com.example.categoria.models.CategoriaModel;
import com.example.categoria.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> findAll(){
        List<CategoriaModel> request = categoriaService.findAll();
        return ResponseEntity.ok().body(request);
    }

    @PostMapping
    public ResponseEntity<CategoriaModel> criarCategoria(@RequestBody CategoriaModel categoriaModel){
        CategoriaModel request = categoriaService.criarCategoria(categoriaModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoriaModel).toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @GetMapping("/{id}")
    public Optional<CategoriaModel> buscarId(@PathVariable Long id){
        return categoriaService.buscarId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        categoriaService.deletar(id);
    }

}
