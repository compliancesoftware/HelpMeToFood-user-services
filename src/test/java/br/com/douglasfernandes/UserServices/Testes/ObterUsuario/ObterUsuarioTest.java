package br.com.douglasfernandes.UserServices.Testes.ObterUsuario;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:UsuarioTestes", tags = "@ObterUsuarioTest", glue = "br.com.douglasfernandes.UserServices.Testes.ObterUsuario", monochrome = true, dryRun = false)
public class ObterUsuarioTest {

}
