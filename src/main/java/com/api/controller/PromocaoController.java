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
import com.api.entity.Promocao;
import com.api.service.ImagemService;
import com.api.service.PromocaoService;
import com.google.common.net.HttpHeaders;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle das promoções")
@RestController
@RequestMapping("/api/promocao")
public class PromocaoController {

	@Autowired
	PromocaoService service;

	@Autowired
	ImagemService imagemService;

	@ApiOperation(value = "Cadastra uma promoção.")
	@PostMapping()
	public Promocao create(@RequestBody Promocao promocao) {
		Promocao entity = service.create(promocao);
		Imagem imagem = new Imagem();
		imagem.setPromocao(entity);
		imagemService.create(imagem);
		return entity;
	}
	
	@ApiOperation(value = "Uploade de Imagem.")
	@PostMapping("/upload_anuncio_image/{codigo}")
	public void uploadImagem(@RequestParam MultipartFile file, @PathVariable(value = "codigo") long codigo) {
		String nome = StringUtils.cleanPath(file.getOriginalFilename());
		Imagem imagem = imagemService.findByPromocao(codigo);
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
    	Imagem imagem = imagemService.findByPromocao(codigo);    	
        String mimeType = request.getServletContext().getMimeType(imagem.getNome());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + imagem.getNome())
                .body(imagem.getImagem());
    }

	@ApiOperation(value = "Atualiza um promoção.")
	@PutMapping()
	public Promocao update(@RequestBody Promocao promocao) {
		return service.update(promocao);
	}

	@ApiOperation(value = "Busca uma promoção por código.")
	@GetMapping(value = "/{codigo}")
	public Promocao findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}

	@ApiOperation(value = "Retorna uma lista com todas as promoções.")
	@GetMapping("")
	public List<Promocao> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Deleta uma promoção por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		service.delete(codigo);
		return ResponseEntity.ok().build();
	}
}
