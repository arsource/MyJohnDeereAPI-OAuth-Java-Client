package com.deere.api.axiom.generated.v3;

import java.util.List;

public class ContributionDefinition {

    protected ContributionDefinitionDetails details;
    protected List<ActionDefinition> actionDefinitions;
    protected ContributionProduct contributionProduct;

    public ContributionDefinitionDetails getDetails() {
        return details;
    }

    public void setDetails(ContributionDefinitionDetails details) {
        this.details = details;
    }

    public List<ActionDefinition> getActionDefinitions() {
        return actionDefinitions;
    }

    public void setActionDefinitions(List<ActionDefinition> actionDefinitions) {
        this.actionDefinitions = actionDefinitions;
    }

    public ContributionProduct getContributionProduct() {
        return contributionProduct;
    }

    public void setContributionProduct(ContributionProduct contributionProduct) {
        this.contributionProduct = contributionProduct;
    }
}
