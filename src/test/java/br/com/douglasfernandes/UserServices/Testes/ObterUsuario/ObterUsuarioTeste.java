package br.com.douglasfernandes.UserServices.Testes.ObterUsuario;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:UsuarioTestes", tags = "@ObterUsuarioTeste",
        glue = "br.com.douglasfernandes.UserServices.Testes.ObterUsuario", monochrome = true, dryRun = false)
public class ObterUsuarioTeste {

}
