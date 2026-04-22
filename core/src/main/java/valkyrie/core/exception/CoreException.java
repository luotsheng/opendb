package valkyrie.core.exception;

/**
 * @author Luo Tiansheng
 * @since 2026/4/13
 */
public class CoreException extends RuntimeException
{
        public CoreException()
        {
        }

        public CoreException(String message)
        {
                super(message);
        }

        public CoreException(String message, Throwable cause)
        {
                super(message, cause);
        }

        public CoreException(Throwable cause)
        {
                super(cause);
        }

        public CoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
        {
                super(message, cause, enableSuppression, writableStackTrace);
        }
}
