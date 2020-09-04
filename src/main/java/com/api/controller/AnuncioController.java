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

import com.api.entity.Anuncio;
import com.api.entity.Imagem;
import com.api.service.AnuncioService;
import com.api.service.ImagemService;
import com.google.common.net.HttpHeaders;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos anuncios")
@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController {

	@Autowired
	AnuncioService service;
	
	@Autowired
	ImagemService imagemService;

	@ApiOperation(value = "Cadastra um anuncio.")
	@PostMapping()
	public Anuncio create(@RequestBody Anuncio anuncio) {
		Anuncio entity = service.create(anuncio);
		Imagem imagem = new Imagem();
		imagem.setAnuncio(entity);
		imagemService.create(imagem);
		return entity;
	}

	@ApiOperation(value = "Uploade de Imagem.")
	@PostMapping("/upload_anuncio_image/{codigo}")
	public void uploadImagem(@RequestParam MultipartFile file, @PathVariable(value = "codigo") long codigo) {
		String nome = StringUtils.cleanPath(file.getOriginalFilename());
		Imagem imagem = imagemService.findByAnuncio(codigo);
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
    	Imagem imagem = imagemService.findByAnuncio(codigo);    	
        String mimeType = request.getServletContext().getMimeType(imagem.getNome());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + imagem.getNome())
                .body(imagem.getImagem());
    }
		
/*
	@ApiOperation(value = "Download de Imagem.")
	@GetMapping("/download_image/{codigo}")
	public ResponseEntity<Resource> downloadFile(@PathVariable(value = "codigo") Long codigo,
			HttpServletRequest request) {
		Anuncio anuncio = service.findById(codigo);
		String contentType = null;

		try {
			contentType = request.getServletContext().getMimeType(anuncio.getImagem().toString());
		} catch (Exception e) {
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + codigo + "\"").body(null);
	}
	
	@ApiOperation(value = "Download de Imagem.")
	@GetMapping("/download_image/{codigo}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable(value = "codigo") Long codigo, HttpServletRequest request) {
    	Anuncio anuncio = service.findById(codigo);    	
        String mimeType = request.getServletContext().getMimeType(anuncio.getTitulo());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + anuncio.getCodigo())
                .body(anuncio.getImagem());
    }
	*/
	
    

	@ApiOperation(value = "Atualiza um anuncio.")
	@PutMapping()
	public Anuncio update(@RequestBody Anuncio anuncio) {
		return service.update(anuncio);
	}

	@ApiOperation(value = "Busca um anuncio por código.")
	@GetMapping(value = "/{codigo}")
	public Anuncio findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}

	@ApiOperation(value = "Retorna uma lista com todos os anuncio.")
	@GetMapping("")
	public List<Anuncio> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Deleta um anuncio por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		service.delete(codigo);
		return ResponseEntity.ok().build();
	}
}
