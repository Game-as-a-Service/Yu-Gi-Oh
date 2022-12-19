package tw.gaas.yugioh.domain.error

@Deprecated(message = "僅用於測試使用", level = DeprecationLevel.WARNING)
class DemoException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)
