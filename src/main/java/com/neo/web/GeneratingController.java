package com.neo.web;

import com.neo.util.UserConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class GeneratingController {
    private boolean portInvalidator(String port){
        if(port.equals("")){
            return false;
        }
        else{
            char[] portArray = port.toCharArray();
            for(char c : portArray){
                if(!Character.isDigit(c)){
                    return false;
                }
            }

            int portNumber = Integer.parseInt(port);
            if(portNumber < 1 || portNumber > 65535){
                return false;
            }
        }

        return true;
    }

    private String configInvalidator(UserConfig config) {
        if(config.getOS().equals("Choose...")){
            return "Please provide OS you want.";
        }

        if(!portInvalidator(config.getContainerPort())){
            return "Please provide correct container port.";
        }

        if(!portInvalidator(config.getHostPort())){
            return "Please provide correct host port.";
        }

        if(config.getPath().equals("")){
            return "Please provide path.";
        }

        return "Valid input";
    }

    private String dockerCommandGenerator(UserConfig config) {
        String startCommand = "docker run ";
        startCommand += "-v " + config.getPath() + ":/workspace ";
        startCommand += "-p " + config.getContainerPort() + ":" + config.getHostPort();
        startCommand += config.getUnconfined() == null ?
                "" : " --security-opt seccomp=unconfined";
        startCommand += " " + config.getOS();
        return startCommand;
    }

    @RequestMapping(value = "/generating", method= RequestMethod.POST)
    public String hello(UserConfig config, Model model) {
        String validateResult = configInvalidator(config);

        if(validateResult.equals("Valid input")){
            String command = dockerCommandGenerator(config);
            model.addAttribute("dockerCommand", command);
            return "DockerCommand";
        }

        model.addAttribute("errorMessage", validateResult);
        return "DockerEnvInit";
    }
}
