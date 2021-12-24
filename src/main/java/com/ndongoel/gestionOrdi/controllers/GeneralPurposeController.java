/* Created by El Hadji M. NDONGO  */
/* by El Hadji M. NDONGO      */
/* on 12/23/2021              */
/* Project: gestionOrdi       */

package com.ndongoel.gestionOrdi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralPurposeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }
}
