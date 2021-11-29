package com.epam.ratingmovies.util;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.dao.entity.UserRole;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.tagext.TagSupport;

import static jakarta.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static jakarta.servlet.jsp.tagext.Tag.SKIP_BODY;

    public class AccessTag extends TagSupport {
        private static final String GUEST = "GUEST";
        private static final String NOT_GUEST = "NOT_GUEST";
        private String role;

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public int doStartTag() {
            HttpSession session = pageContext.getSession();
            UserRole role = (UserRole) session.getAttribute(Attribute.ROLE);
            if (role == null) {
                if (GUEST.equalsIgnoreCase(this.role)) {
                    return EVAL_BODY_INCLUDE;
                }
            } else if (role.toString().equalsIgnoreCase(this.role)
                    || NOT_GUEST.equalsIgnoreCase(this.role)) {
                return EVAL_BODY_INCLUDE;
            }
            return SKIP_BODY;
        }
    }
