package com.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.Imagem;
import com.api.entity.Produto;
import com.api.service.ImagemService;
import com.api.service.ProdutoService;
import com.google.common.net.HttpHeaders;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos produtos")
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	@Autowired
	ImagemService imagemService;
	
	@ApiOperation(value = "Cadastra um produto.")
	@PostMapping()
	public Produto create(@RequestBody Produto produto){
		Produto entity = service.create(produto);
		Imagem imagem = new Imagem();
		imagem.setProduto(entity);
		imagemService.create(imagem);
		return entity;
	}
	
	@ApiOperation(value = "Uploade de Imagem.")
	@PostMapping("/upload_anuncio_image/{codigo}")
	public void uploadImagem(@RequestParam MultipartFile file, @PathVariable(value = "codigo") long codigo) {
		String nome = StringUtils.cleanPath(file.getOriginalFilename());
		Imagem imagem = imagemService.findByProduto(codigo);
		try {
			imagem.setNome(nome);
			imagem.setImagem(file.getBytes());
			imagemService.create(imagem);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value = "Download de Imagem.")
	@GetMapping("/download_anuncio_image/{codigo}")
    ResponseEntity<byte[]> downLoadFile(@PathVariable(value = "codigo") Long codigo, HttpServletRequest request) {
    	Imagem imagem = imagemService.findByProduto(codigo);    	
        String mimeType = request.getServletContext().getMimeType(imagem.getNome());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + imagem.getNome())
                .body(imagem.getImagem());
    }
	
	@ApiOperation(value = "Atualiza um produto.")
	@PutMapping()
	public Produto update(@RequestBody Produto produto) {
		return service.update(produto);
	}
	
	@ApiOperation(value = "Busca um produto por código.")
	@GetMapping(value = "/{codigo}")
	public Produto findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produtos por codigo da gondola.")
	@GetMapping("/gondola/{codigo}")
	public List<Produto> findByGondolaCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findByGondolaCodigo(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produtos por cdescricao")
	@GetMapping("/produtos_descricao/{descricao}")
	public List<Produto> findByDescricao(@PathVariable(value = "descricao") String descricao) {
		return service.findByDescricao(descricao);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produtos.")
	@GetMapping("")
	public List<Produto> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produtos com destaque igual a true.")
	@GetMapping("/destaque")
	public List<Produto> findbyDestaqueEqualsTrue() {
		return service.findByDestaque();
	}
	
	@ApiOperation(value = "Deleta um produto por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
