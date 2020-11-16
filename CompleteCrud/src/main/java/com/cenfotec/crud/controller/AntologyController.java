package com.cenfotec.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.crud.domain.Antology;
import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.service.AntologyService;
import com.cenfotec.crud.service.ArticleService;

@Controller
public class AntologyController {

	@Autowired
	AntologyService anthologyService;
	
	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Antology());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Antology antology, BindingResult result, Model model) {
		anthologyService.save(antology);
		return "index";
	}
	
	/*
	@RequestMapping(value = "/modificar/{id}",  method = RequestMethod.GET)
	public String modificarPage(Model model, @PathVariable("id") long id) {		
		model.addAttribute("antology",anthologyService.get(id));
		return "modificar";
	}	
	
	@RequestMapping(value = "/modificar/{id}",  method = RequestMethod.PUT)
	public String modificarAction(Antology antology, BindingResult result, Model model) {
		anthologyService.save(antology);
		return "index";
	}
	*/
	
	@GetMapping("/modificar/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Antology antology = anthologyService.get(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid antology:" + id));
	    
	    model.addAttribute("antology", antology);
	    return "modificar";
	}
	
	@PostMapping("/modificar/{id}")
	public String updateAntology(@PathVariable("id") long id, Antology antology, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	antology.setId(id);
	        return "modificar";
	    }
	        
	    anthologyService.save(antology);
	    model.addAttribute("antology", anthologyService.getAll());
	    return "index";
	}
	
	@GetMapping("/detalles/{id}")
	public String showDetalles(@PathVariable("id") long id, Model model) {
	    Antology antology = anthologyService.get(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid antology:" + id));
	    
	    model.addAttribute("antologyData", antology);
	    return "detalles";
	}

	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}
	
	@RequestMapping(value="/detalle/{id}")
	public String saveEdition(Model model, @PathVariable long id) {
		Optional<Antology> possibleData = anthologyService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("antologyData",possibleData.get());
			return "detalle";	
		}
		return "notfound";
	}

	@RequestMapping(value="/insertarArticulo/{id}")
	public String recoverForAddArticle(Model model, @PathVariable long id) {
		Optional<Antology> antology = anthologyService.get(id);
		Article newArticle = new Article();
		if (antology.isPresent()) {
			newArticle.setAnthology(antology.get());
			model.addAttribute("antology",antology.get());
			model.addAttribute("article",newArticle);
			return "insertarArticulo";	
		}
		return "notfound";
	}
	
	@RequestMapping(value="/insertarArticulo/{id}", method = RequestMethod.POST)
	public String saveArticle(Article article, Model model, @PathVariable long id) {
		Optional<Antology> antology = anthologyService.get(id);
		if (antology.isPresent()) {
			article.setAnthology(antology.get());
			articleService.save(article);
			return "index";
		}
		return "errorInsertarArticulo";
		
	}
	
}
