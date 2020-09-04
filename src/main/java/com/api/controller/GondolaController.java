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

import com.api.entity.Gondola;
import com.api.entity.Imagem;
import com.api.service.GondolaService;
import com.api.service.ImagemService;
import com.google.common.net.HttpHeaders;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle das gondolas")
@RestController
@RequestMapping("/api/gondola")
public class GondolaController {

	@Autowired
	GondolaService service;
	
	@Autowired
	ImagemService imagemService;
	
	@ApiOperation(value = "Cadastra uma gondola.")
	@PostMapping()
	public Gondola create(@RequestBody Gondola gondola){
		Gondola entity = service.create(gondola);
		Imagem imagem = new Imagem();
		imagem.setGondola(entity);
		imagemService.create(imagem);
		return entity ;
	}
	
	@ApiOperation(value = "Uploade de Imagem.")
	@PostMapping("/upload_anuncio_image/{codigo}")
	public void uploadImagem(@RequestParam MultipartFile file, @PathVariable(value = "codigo") long codigo) {
		String nome = StringUtils.cleanPath(file.getOriginalFilename());
		Imagem imagem = imagemService.findByGondola(codigo);
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
    	Imagem imagem = imagemService.findByGondola(codigo);    	
        String mimeType = request.getServletContext().getMimeType(imagem.getNome());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + imagem.getNome())
                .body(imagem.getImagem());
    }	
	
	@ApiOperation(value = "Atualiza uma gondola.")
	@PutMapping()
	public Gondola update(@RequestBody Gondola avaliacao) {
		return service.update(avaliacao);
	}
	
	@ApiOperation(value = "Busca uma gondola por código.")
	@GetMapping(value = "/{codigo}")
	public Gondola findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todas as gondolas.")
	@GetMapping("")
	public List<Gondola> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta uma gondola por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
