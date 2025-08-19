package com.produtoapi.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.produtoapi.model.Produto;

import java.util.Arrays;
import java.util.List;

public class CRUDJavaClient {
	private static final String BASE_URL = "http://localhost:8080/produtos";
	private RestTemplate restTemplate;
	
	public CRUDJavaClient(){
		this.restTemplate = new RestTemplate();
	}
	
	//Metodo para listar todos os produtos
	public void listarTodos() {
		ResponseEntity<Produto[]> response = restTemplate.getForEntity(BASE_URL, Produto[].class);
		List<Produto> produtos = Arrays.asList(response.getBody());
		produtos.forEach(produto ->{
			System.out.println("ID: " + produto.getId());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Preco: " + produto.getPreco());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Status: " + produto.getStatus());
		});
	}
		
		//Metodo para salvar um produto
		public Produto salvar(Produto produto) {
			
			HttpEntity<Produto> request = new HttpEntity<>(produto);
			return restTemplate.postForObject(BASE_URL, request, Produto.class);
		}
		
		//Metodo para deletar um produto
		public void deletar(int id){
			restTemplate.delete(BASE_URL + "/" + id);
		}
		
		//Metodo para atualizar um produto existente
		public Produto atualizar(int id, Produto produto) {
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
			ResponseEntity<Produto> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, request, Produto.class);
			return response.getBody();
		}
		
		//Meotodo para buscar um produto pelo id
		public Produto findById(int id) {
			ResponseEntity<Produto> response = restTemplate.getForEntity(BASE_URL + "/" + id, Produto.class);
			return response.getBody();
		}
	public static void main(String args[]) {
		CRUDJavaClient client = new CRUDJavaClient();
		
		//============Criar um novo produto============
		/*
		  Produto novoProduto = new Produto();
		  novoProduto.setNome("Skate Profissional");
		  novoProduto.setPreco(300);
		  novoProduto.setQuantidade(20);
		  novoProduto.setStatus("Disponivel");
		  
		  System.out.println("Criar um novo produto");
		  client.salvar(novoProduto);
		 client.listarTodos(); //Lista todos os produtos apos a inserção
		 */
		
		//================Atualiza produto===================
		
		/*
		 Produto atualizarProduto = client.findById(3);
		 atualizarProduto.setNome("Skate Infantil");
		 atualizarProduto.setPreco(150);
		 
		 System.out.println("Atualizar o produto");
		 client.atualizar(atualizarProduto.getId(), atualizarProduto);
		 
		 //Listar todos os produtos novamente
		 client.listarTodos();
		 */
		
		//============= Deletar o Produto ===============
		
		/*
		 System.out.println("Deletar o produto");
		 client.deletar(1);
		 
		 //Listar todos os produtos novamente
		  client.listarTodos();
		 */
		
	}
}

