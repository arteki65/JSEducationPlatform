package pl.aptewicz.jseducationplatform.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class MainController {

	private static final String INDEX_TEMPLATE = "index";
	private static final String APPLICATION_SOURCES_LOCATION = "classpath*:/static/*.html";
	private static final String APPLICATION_PATHS = "applicationPaths";

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(APPLICATION_SOURCES_LOCATION);

			Collection<String> applicationPaths = new ArrayList<>();
			Arrays.stream(resources).filter(resource -> resource.getFilename().endsWith(".html"))
					.forEach(resource -> applicationPaths.add(resource.getFilename()));
			model.addAttribute(APPLICATION_PATHS, applicationPaths);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return INDEX_TEMPLATE;
	}
}
