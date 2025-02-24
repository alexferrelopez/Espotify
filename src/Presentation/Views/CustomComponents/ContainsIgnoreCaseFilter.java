package Presentation.Views.CustomComponents;

import javax.swing.*;

/**
 * Classe que ens serveix per ignorar el case filter
 */
public class ContainsIgnoreCaseFilter extends RowFilter<Object, Object> {

    private final String match;

    /**
     * Constructor for a row filter that compares in a non-caseSensitive way.
     *
     * @param match string being compared.
     */
    public ContainsIgnoreCaseFilter(String match) {
        this.match = match.toLowerCase();
    }

    /**
     * Method to check if the match is contained in a row.
     *
     * @param entry a non-<code>null</code> object that wraps the underlying
     *              object from the model
     * @return true if match is contained in the row.
     */
    @Override
    public boolean include(javax.swing.RowFilter.Entry<?, ?> entry) {
        for (int i = entry.getValueCount() - 1; i >= 0; i--) {
            if (entry.getStringValue(i).toLowerCase().contains(match)) {
                return true;
            }
        }
        return false;
    }
}