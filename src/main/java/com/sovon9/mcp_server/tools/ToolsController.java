package com.sovon9.mcp_server.tools;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.mcp.annotation.context.McpSyncRequestContext;
import org.springframework.stereotype.Component;

@Component
public class ToolsController {

    @McpTool(name = "best-stocks")
    public String bestStocks(@McpToolParam(description = "provide the month of with needs best stocks for") String month, McpSyncRequestContext context)
    {
        String response = "Lupin, wockpharma";
        context.info("choosing best stock....");
        if(!context.sampleEnabled())
        {
            return response;
        }
        McpSchema.CreateMessageResult result = context.sample(message ->
                message.systemPrompt("act as a market expert and provide analysis on stocks")
                .message("Provide best stock performance in India in the month of "+month));
        String analysisresult = ((McpSchema.TextContent) result.content()).text();
        return analysisresult;
    }

}
