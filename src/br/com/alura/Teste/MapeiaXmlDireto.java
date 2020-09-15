package br.com.alura.Teste;

import java.io.File;
import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.alura.Model.Produto;
import br.com.alura.Model.Venda;

public class MapeiaXmlDireto {
	
	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);
	
		//xmlParaObjeto(jaxbContext);
		
		objetoParaXml(jaxbContext);
		
	}

	private static void objetoParaXml(JAXBContext jaxbContext) throws JAXBException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		Venda venda = new Venda();
		venda.setFormaDePagamento("Débito");
		venda.setProdutos(Arrays.asList(
				new Produto("Livro de Java", 100.0), 
				new Produto("Livro de XML", 200.0),
				new Produto("Livro de Python", 300.0)));
		
		StringWriter writer = new StringWriter();
		marshaller.marshal(venda, writer);
		
		System.out.println(writer.toString());
	}

	private static void xmlParaObjeto(JAXBContext jaxbContext) throws JAXBException {	
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		Venda venda = (Venda) unmarshaller.unmarshal(new File("src/vendas.xml"));	
		
		System.out.println(venda);
	}

}
