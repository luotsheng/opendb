package valkyrie.app.exception;

import valkyrie.app.widgets.dialog.VkDialogHelper;

/**
 * @author Luo Tiansheng
 * @since 2026/4/13
 */
public class ApplicationException extends RuntimeException
{

        public ApplicationException(String message)
        {
                super(message);
                VkDialogHelper.alert(message);
        }
}
